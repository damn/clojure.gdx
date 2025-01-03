(ns clojure.gdx.scene2d.ui.button-group
  "Manages a group of buttons to enforce a minimum and maximum number of checked buttons. This enables \"radio button\" functionality and more. A button may only be in one group at a time.

The canCheck(Button, boolean) method can be overridden to control if a button check or uncheck is allowed."
  (:refer-clojure :exclude [remove])
  (:import (com.badlogic.gdx.scenes.scene2d.ui Button ButtonGroup)))

(defn add [button-group button]
  (ButtonGroup/.add button-group ^Button button))

(defn remove [button-group button]
  (ButtonGroup/.remove button-group ^Button button))

(defn checked
  "The first checked button, or nil."
  [button-group]
  (ButtonGroup/.getChecked button-group))
