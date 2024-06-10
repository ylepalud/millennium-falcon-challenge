import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import {ChallengeResponse} from "./challenge-response/challenge-response";
import {SolveRequest} from "./api/model/solve-request";

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, ChallengeResponse],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  request: SolveRequest = {
    "countDown": 10,
    "bountyHunters": [
      {"planet": "Tatooine", "day": 4 },
      {"planet": "Dagobah", "day": 5 }
    ]
  };
}
