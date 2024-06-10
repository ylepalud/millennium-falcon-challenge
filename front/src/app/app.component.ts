import {Component} from '@angular/core';
import { RouterOutlet } from '@angular/router';
import {ChallengeResponse} from "./challenge-response/challenge-response";
import {SolveRequest} from "./api/model/solve-request";
import {FileReaderComponent} from "./file-reader/file-reader.component";
import {EmpireFile} from "./path/empire-file";
import {NgIf} from "@angular/common";

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, ChallengeResponse, FileReaderComponent, NgIf],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {

  request: SolveRequest | null = null;

  handleEmpireFileUpload($event: EmpireFile) {
    this.request = this.map($event);
  }

  private map(file: EmpireFile): SolveRequest {
    return {
      countDown: file.countdown,
      bountyHunters: file.bounty_hunters
    };
  }
}
