import {createReducer, on} from "@ngrx/store";
import * as PlayComponentActions from "./play.actions";

export interface PlayState {
  email: string;
  country: string;
  error: any | undefined;
}

export const initialState: PlayState = {
  email: "",
  country: "",
  error: undefined,
};

export const playReducer = createReducer(
  initialState,
  on(PlayComponentActions.play, (state, {email, country}) => ({...state, email, country})),
  on(PlayComponentActions.reset, (state) => ({...state, email: "", country: ""})),
  on(PlayComponentActions.played, (state) => ({...state, error: undefined})),
  on(PlayComponentActions.playFailed, (state, {error}) => ({...state, error})),
);
