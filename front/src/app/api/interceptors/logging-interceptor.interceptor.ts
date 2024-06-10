import {HttpEventType, HttpInterceptorFn} from '@angular/common/http';
import {tap} from "rxjs";

export const loggingInterceptorInterceptor: HttpInterceptorFn = (req, next) => {
  return next(req).pipe(tap(event => {
    switch (event.type) {
      case HttpEventType.Sent:
        console.log(req.url, 'requesting the backend', event.type);
        break;
      case HttpEventType.Response:
        console.log(req.url, 'returned a response with status', event.status);
        break;
    }
  }));
};
