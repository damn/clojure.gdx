(ns clojure.gdx.scene2d.stage
  "A 2D scene graph containing hierarchies of actors. Stage handles the viewport and distributes input events.

setViewport(Viewport) controls the coordinates used within the stage and sets up the camera used to convert between stage coordinates and screen coordinates.

A stage must receive input events so it can distribute them to actors. This is typically done by passing the stage to Gdx.input.setInputProcessor. An InputMultiplexer may be used to handle input events before or after the stage does. If an actor handles an event by returning true from the input method, then the stage's input method will also return true, causing subsequent InputProcessors to not receive the event.

The Stage and its constituents (like Actors and Listeners) are not thread-safe and should only be updated and queried from a single thread (presumably the main render thread). Methods should be reentrant, so you can update Actors and Stages from within callbacks and handlers."
  (:import (com.badlogic.gdx.scenes.scene2d Stage)))

(defn add-actor
  "Adds an actor to the root of the stage.

  See Also:
  Group.addActor(Actor)"
  [stage actor]
  (Stage/.addActor stage actor))

(defn clear
  "Removes the root's children, actions, and listeners."
  [stage]
  (Stage/.clear stage))

(defn hit
  "Returns the Actor at the specified location in stage coordinates. Hit testing is performed in the order the actors were inserted into the stage, last inserted actors being tested first. To get stage coordinates from screen coordinates, use screenToStageCoordinates(Vector2).

Parameters:
    touchable - If true, the hit detection will respect the touchability.
Returns:
    May be null if no actor was hit."
  [stage x y touchable?]
  (Stage/.hit stage x y touchable?))

(defn act
  "Calls act(float) with Graphics.getDeltaTime(), limited to a minimum of 30fps."
  [stage]
  (Stage/.act stage))

(defn draw [stage]
  (Stage/.draw stage))

(defn root
  "Returns the root group which holds all actors in the stage."
  [stage]
  (Stage/.getRoot stage))
