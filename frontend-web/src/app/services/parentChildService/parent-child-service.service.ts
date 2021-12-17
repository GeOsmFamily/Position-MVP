import { Injectable } from '@angular/core';
import { Observable, Subject } from 'rxjs';
import { Feature, Point } from 'src/app/modules/ol';

@Injectable({
  providedIn: 'root'
})
export class ParentChildServiceService {

  constructor() { }
  private subject = new Subject<any>();

  sendData(message: any[]) {
      this.subject.next(message);
  }

  clearData() {
      this.subject.next();
  }

  getData(): Observable<any[]> {
      return this.subject.asObservable();
  }
}
