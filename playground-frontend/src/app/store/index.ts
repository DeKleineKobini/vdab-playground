import {playReducer, PlayState} from "./play.reducer";
import {counterReducer} from "./counter.reducer";
import {PlayEffects} from "./play.effects";
import {ActionReducer} from "@ngrx/store";
import {Type} from "@angular/core";

export interface AppState {
  count: number;
  play: PlayState;
}

export const reducers: {
  [key: string]: ActionReducer<any>
} = {
  count: counterReducer,
  play: playReducer,
};

export const effects: Type<any>[] = [
  PlayEffects,
];
