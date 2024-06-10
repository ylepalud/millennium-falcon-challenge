import {Component, Input} from '@angular/core';
import {way} from "../api/model/way";

@Component({
  selector: 'app-path',
  standalone: true,
  imports: [],
  template: `
      @for (way of ways; track way.step; let count = $count) {
        <li>In {{ way.planet }} do a {{ way.action.toLocaleLowerCase() }} for {{ way.timeTravel }} day{{ count <= 0 ? "s" : "" }} </li>
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
