import {Injectable} from "@angular/core";
import {PlayDto} from "./play-form/play.dto";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {IncrementDto} from "./increment-form/increment.dto";

@Injectable({
  providedIn: "root",
})
export class PlayService {

  constructor(private httpClient: HttpClient) {
  }

  play(dto: PlayDto): Observable<void> {
    return this.httpClient.post<void>("/playground/api/play", dto);
  }

  increment(dto: IncrementDto): Observable<void> {
    return this.httpClient.post<void>("/playground/api/play/increment", dto);
  }

}
