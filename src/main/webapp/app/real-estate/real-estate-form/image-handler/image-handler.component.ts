import { Component, ElementRef, EventEmitter, Input, OnInit, Output, ViewChild } from '@angular/core';
import { FormMode } from 'app/real-estate/models/formMode';
import { ImageHandlerService } from 'app/real-estate/real-estate-form/image-handler/image-handler.service';

@Component({
  selector: 'jhi-image-handler',
  templateUrl: './image-handler.component.html',
  styleUrls: ['./image-handler.component.scss']
})
export class ImageHandlerComponent implements OnInit {
  @ViewChild('file', { static: false }) file: ElementRef;
  @Output() imageUrlsChanged = new EventEmitter();
  @Input() mode: FormMode;

  _imageUrls: string[];
  @Input() set imageUrls(value: string[]) {
    value.forEach(v => this.downloadImage(v));
  }

  get imageUrls(): string[] {
    return this._imageUrls;
  }
  @Input() realEstateId: number;

  chosenImageFile: File;
  imagesToShow: any[];

  FormMode = FormMode;

  constructor(public imageHandlerService: ImageHandlerService) {}

  ngOnInit() {}

  onFileChange() {
    const files: { [key: string]: File } = this.file.nativeElement.files;
    this.chosenImageFile = files[0];

    if (!this.chosenImageFile) {
      return;
    }

    const formData = new FormData();
    formData.append('file', this.chosenImageFile);
    this.imageHandlerService.upload(this.realEstateId, formData).subscribe(() => {
      this.imageUrlsChanged.emit();
    });
  }

  onFileClicked() {
    this.file.nativeElement.value = null;
  }

  deleteImage(url: string): void {
    // TODO
    // this.imageHandlerService.delete(this.realEstateId, url).subscribe(
    //   () => {
    //      this.imageUrlsChanged.emit();
    //   });
  }

  select() {
    if (this.file) {
      this.file.nativeElement.click();
    }
  }

  downloadImage(imageUrl) {
    this.imageHandlerService.download(imageUrl).subscribe(result => {
      this.createImageFromBlob(result);
    });
  }

  createImageFromBlob(image: Blob) {
    const reader = new FileReader();
    reader.addEventListener(
      'load',
      () => {
        this.imagesToShow.push(reader.result);
      },
      false
    );

    if (image) {
      reader.readAsDataURL(image);
    }
  }
}
