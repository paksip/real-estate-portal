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
    // TODO: URL alapján elérni a képeket
    this._imageUrls = value;
  }

  get ImageUrls(): string[] {
    return this._imageUrls;
  }
  @Input() realEstateId: number;

  chosenImageFile: File;

  FormMode = FormMode;

  constructor(private imageHandlerService: ImageHandlerService) {}

  ngOnInit() {}

  onFileChange() {
    const files: { [key: string]: File } = this.file.nativeElement.files;
    this.chosenImageFile = files[0];

    if (!this.chosenImageFile) {
      return;
    }
    this.imageHandlerService.upload(this.realEstateId, this.chosenImageFile).subscribe(() => {
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
}
