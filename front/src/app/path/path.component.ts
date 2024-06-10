import {Component, Input} from '@angular/core';
import {way} from "../api/model/way";

@Component({
  selector: 'app-path',
  standalone: true,
  imports: [],
  templateUrl: './path.component.html',
  styleUrl: './path.component.css'
})
export class PathComponent {

  @Input() ways!: Array<way>;

}
