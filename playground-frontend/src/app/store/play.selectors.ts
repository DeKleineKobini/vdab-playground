import {PlayState} from "./play.reducer";
import {AppState} from "./index";

export const selectError = (state: PlayState) => {
  // FIXME - Investigate weird behaviour.
  // It's expecting me to request a PlayState, but gives me the AppState instead.
  return (state as unknown as AppState).play.error;
};
