package bme.aut.szarch.realestateportal.repository.kotlin.realstatespecification;

import bme.aut.szarch.realestateportal.domain.kotlin.RealEstateEntity;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;


public class RealEstateSpecificationBuilder {

    private final List<SpecSearchCriteria> params;

    public RealEstateSpecificationBuilder() {
        params = new ArrayList<>();
    }

    // API

    public final RealEstateSpecificationBuilder with(final String key, final String operation, final Object value, final String prefix, final String suffix) {
        return with(null, key, operation, value, prefix, suffix);
    }

    public final RealEstateSpecificationBuilder with(final String orPredicate, final String key, final String operation, final Object value, final String prefix, final String suffix) {
        SearchOperation op = SearchOperation.getSimpleOperation(operation.charAt(0));
        if (op != null) {
            if (op == SearchOperation.EQUALITY) { // the operation may be complex operation
                final boolean startWithAsterisk = prefix != null && prefix.contains(SearchOperation.ZERO_OR_MORE_REGEX);
                final boolean endWithAsterisk = suffix != null && suffix.contains(SearchOperation.ZERO_OR_MORE_REGEX);

                if (startWithAsterisk && endWithAsterisk) {
                    op = SearchOperation.CONTAINS;
                } else if (startWithAsterisk) {
                    op = SearchOperation.ENDS_WITH;
                } else if (endWithAsterisk) {
                    op = SearchOperation.STARTS_WITH;
                }
            }
            params.add(new SpecSearchCriteria(orPredicate, key, op, value));
        }
        return this;
    }

    public Specification<RealEstateEntity> build() {
        if (params.size() == 0)
            return null;

        Specification<RealEstateEntity> result = new RealEstateSpecification(params.get(0));

        for (int i = 1; i < params.size(); i++) {
            result = params.get(i).isOrPredicate()
                ? Specification.where(result).or(new RealEstateSpecification(params.get(i)))
                : Specification.where(result).and(new RealEstateSpecification(params.get(i)));
        }

        return result;
    }

    public final RealEstateSpecificationBuilder with(RealEstateSpecification spec) {
        params.add(spec.getCriteria());
        return this;
    }

    public final RealEstateSpecificationBuilder with(SpecSearchCriteria criteria) {
        params.add(criteria);
        return this;
    }
}
