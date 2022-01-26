import {Injectable} from "@angular/core";
import {Actions, createEffect, ofType} from "@ngrx/effects";
import {of} from "rxjs";
import {catchError, exhaustMap, map} from "rxjs/operators";
import * as PlayActions from "./play.actions";
import {PlayService} from "../services/play.service";

@Injectable()
export class PlayEffects {

  play$ = createEffect(() =>
    this.actions$.pipe(
      ofType(PlayActions.play.type),
      exhaustMap(action => {
          return this.playService.play(action)
            .pipe(
              map(() => PlayActions.played()),
              catchError(error => of(PlayActions.playFailed({error}))),
            );
        },
      ),
    ),
  );

  constructor(
    private actions$: Actions,
    private playService: PlayService,
  ) {
  }


}
