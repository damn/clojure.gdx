(ns clojure.gdx.audio.sound)

(defn play
  "Plays the sound. If the sound is already playing, it will be played again, concurrently.

  Returns:
  the id of the sound instance if successful, or -1 on failure."
  [sound]
  (Sound/.play sound))
