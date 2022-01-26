import {createAction, props} from "@ngrx/store";

const name = "[Play Component]";

export const play = createAction(`${name} Play`, props<{ email: string, country: string }>());
export const played = createAction(`${name} Played`);
export const playFailed = createAction(`${name} Play Failed`, props<{ error: any }>());
export const reset = createAction(`${name} Reset`);
