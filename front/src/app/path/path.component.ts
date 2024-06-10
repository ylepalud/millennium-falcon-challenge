import {Component, Input} from '@angular/core';
import {way} from "../api/model/way";

@Component({
  selector: 'app-path',
  standalone: true,
  imports: [],
  template: `
    <h3>This is the way</h3>
      @for (way of ways; track way.step; let count = $count) {
        <li>{{ way.action.toLocaleLowerCase() }} {{ way.action.toLocaleLowerCase() === 'jump' ? "to" : "on" }} {{ way.planet }} for {{ way.timeTravel }} day{{ count <= 0 ? "s" : "" }} </li>
      } @empty {
        <li>There is no hope ...</li>
      }
  `,
  //templateUrl: './path.component.html',
  styleUrl: './path.component.css'
})
export class PathComponent {

  @Input() ways!: Array<way>;

}
