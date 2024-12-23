(ns clojure.gdx.audio.sound
  "A Sound is a short audio clip that can be played numerous times in parallel. It's completely loaded into memory so only load small audio files. Call the `dispose()` method when you're done using the Sound.

  Sound instances are created via a call to `Audio.newSound(FileHandle)`.

  Calling the `play()` or `play(float)` method will return a long which is an id to that instance of the sound. You can use this id to modify the playback of that sound instance.

  Note: any values provided will not be clamped, it is the developer's responsibility to do so "
  (:import (com.badlogic.gdx.audio Sound)))

(defn play
  "Plays the sound. If the sound is already playing, it will be played again, concurrently.

  Returns:
  the id of the sound instance if successful, or -1 on failure."
  [sound]
  (Sound/.play sound))
