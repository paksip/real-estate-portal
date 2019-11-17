import { Component, ElementRef, Input, OnInit, ViewChild } from '@angular/core';
import { FormMode } from 'app/real-estate/models/formMode';

@Component({
  selector: 'jhi-image-handler',
  templateUrl: './image-handler.component.html',
  styleUrls: ['./image-handler.component.scss']
})
export class ImageHandlerComponent implements OnInit {
  @ViewChild('file', { static: false }) file: ElementRef;
  @Input() mode: FormMode;
  @Input() reservationId: number;
  FormMode = FormMode;
  images: string[];
  constructor() {}

  ngOnInit() {}

  onFileChange() {}

  onFileClicked() {
    this.file.nativeElement.value = null;
  }
}
