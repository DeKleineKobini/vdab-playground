import {createAction} from "@ngrx/store";

const name = "[Counter Component]";

export const increment = createAction(`${name} Increment`);
export const decrement = createAction(`${name} Decrement`);
export const reset = createAction(`${name} Reset`);
