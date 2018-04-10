import { RequestOptions, Http, Response, Headers } from '@angular/http';
import { ReplaySubject } from 'rxjs/ReplaySubject';
import { Subject } from 'rxjs/Subject';
import { Artefact } from './../Interfaces/Developer.d';
import { Observable } from 'rxjs/Observable';
import { Injectable } from '@angular/core';
import { environment } from "../../environments/environment";

@Injectable()
export class ArtefactService {

  private artefactsSubject: Subject<Artefact[]> = new ReplaySubject(1);
  artefacts$ = this.artefactsSubject.asObservable();

  constructor(public http: Http) { 
    this.getArtefacts()
  }


  getArtefacts(): Observable<Artefact[]>{
    this.http.get(`${environment.endpoint}/artefact/`)
      .map(this.extractData)
      .subscribe(
        result => {
          this.artefactsSubject.next(result)
        },
        err    => this.artefactsSubject.error(err)
      ) 
    return this.artefactsSubject.asObservable();
  }

  createArtefact(artefact: any): Promise<Artefact> {
    let headers = new Headers({ 'Content-Type': 'application/json' });
    let options = new RequestOptions({ headers: headers });
    return this.http.post(`${environment.endpoint}/artefact/`, artefact, options)
      .toPromise()
      .then(this.extractData)
      .catch(this.handleError);
  }

  updateArtefact(artefact: any): Promise<Artefact> {
    let headers = new Headers({ 'Content-Type': 'application/json' });
    let options = new RequestOptions({ headers: headers });
    return this.http.put(`${environment.endpoint}/artefact/${artefact.id}`, artefact, options)
      .toPromise()
      .then(this.extractData)
      .catch(this.handleError);
  }

  private extractData(res: Response) {
    const body = res.json();
    return body || {};
  }
  
  private handleError(error: Response | any) {
    let errMsg: string;
    if (error instanceof Response) {
      const body = error.json() || '';
      const err = body.error || JSON.stringify(body);
      errMsg = `${error.status} - ${error.statusText || ''} ${err}`;
    } else {
      errMsg = error.message ? error.message : error.toString();
    }
    console.error(errMsg);
    return Promise.reject(errMsg);
  }

  setMinLevel(artefact: Artefact, min: number){
    artefact.minLevel.min = min
  }
}