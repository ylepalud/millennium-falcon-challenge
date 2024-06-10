import {Component, EventEmitter, Output} from '@angular/core';
import {JsonPipe, NgIf} from "@angular/common";
import {EmpireFile} from "../path/empire-file";

@Component({
  selector: 'app-file-reader',
  standalone: true,
  imports: [
    NgIf,
    JsonPipe
  ],
  templateUrl: './file-reader.component.html',
  styleUrl: './file-reader.component.css'
})
export class FileReaderComponent {
  fileContent: EmpireFile | null = null;

  errorMessage: string | null = null;

  @Output() empireFileChange: EventEmitter<EmpireFile> = new EventEmitter();

  onFileSelected(event: Event): void {
    const input = event.target as HTMLInputElement;
    if (!input.files?.length) {
      return;
    }

    const file = input.files[0];
    const reader = new FileReader();

    reader.onload = () => {
      try {
        const json = JSON.parse(reader.result as string);
        const empireFile = json as EmpireFile;
        if (this.isValid(empireFile)) {
          this.fileContent = empireFile;
          this.errorMessage = null;
        } else {
          this.fileContent = null;
          this.errorMessage = "The file is invalid !";
        }
      } catch (error) {
        console.error('Error parsing JSON', error);
        this.errorMessage = "The file is unreadable !";
      }
    };

    reader.onerror = () => {
      console.error('File reading error', reader.error);
    };

    reader.readAsText(file);
  }

  submitFile() {
    if (this.fileContent) {
      this.empireFileChange.emit(this.fileContent)
    } else {
      this.errorMessage = "Provide a empire file"
    }
  }

  private isValid(empireFile: EmpireFile) {
    const hasInvalidHunter = empireFile.bounty_hunters.filter(hunter => hunter.day != null && hunter.planet !== null).length;
    return empireFile.countdown == null || hasInvalidHunter;
  }
}
