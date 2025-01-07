(ns clojure.gdx.application
  "From https://javadoc.io/doc/com.badlogicgames.gdx/gdx/1.2.0/com/badlogic/gdx/Application.html

  TODO: port docstring to clojure.gdx

  An Application is the main entry point of your project. It sets up a window and rendering surface and manages the different aspects of your application, namely Graphics, Audio, Input and Files. Think of an Application being equivalent to Swing's JFrame or Android's Activity.

An application can be an instance of any of the following:

    a desktop application (see JglfwApplication found in gdx-backends-jglfw.jar)
    an Android application (see AndroidApplication found in gdx-backends-android.jar)
    a HTML5 application (see GwtApplication found in gdx-backends-gwt.jar)
    an iOS application (see IOSApplication found in gdx-backends-robovm.jar)

Each application class has it's own startup and initialization methods. Please refer to their documentation for more information.

While game programmers are used to having a main loop, libgdx employs a different concept to accommodate the event based nature of Android applications a little more. You application logic must be implemented in a ApplicationListener which has methods that get called by the Application when the application is created, resumed, paused, disposed or rendered. As a developer you will simply implement the ApplicationListener interface and fill in the functionality accordingly. The ApplicationListener is provided to a concrete Application instance as a parameter to the constructor or another initialization method. Please refer to the documentation of the Application implementations for more information. Note that the ApplicationListener can be provided to any Application implementation. This means that you only need to write your program logic once and have it run on different platforms by passing it to a concrete Application implementation.

The Application interface provides you with a set of modules for graphics, audio, input and file i/o.

Graphics offers you various methods to output visuals to the screen. This is achieved via OpenGL ES 2.0 or 3.0 depending on what's available an the platform. On the desktop the features of OpenGL ES 2.0 and 3.0 are emulated via desktop OpenGL. On Android the functionality of the Java OpenGL ES bindings is used.

Audio offers you various methods to output and record sound and music. This is achieved via the Java Sound API on the desktop. On Android the Android media framework is used.

Input offers you various methods to poll user input from the keyboard, touch screen, mouse and accelerometer. Additionally you can implement an InputProcessor and use it with Input.setInputProcessor(InputProcessor) to receive input events.

Files offers you various methods to access internal and external files. An internal file is a file that is stored near your application. On Android internal files are equivalent to assets. On the desktop the classpath is first scanned for the specified file. If that fails then the root directory of your application is used for a look up. External files are resources you create in your application and write to an external storage. On Android external files reside on the SD-card, on the desktop external files are written to a users home directory. If you know what you are doing you can also specify absolute file names. Absolute filenames are not portable, so take great care when using this feature.

Net offers you various methods to perform network operations, such as performing HTTP requests, or creating server and client sockets for more elaborate network programming.

The Application also has a set of methods that you can use to query specific information such as the operating system the application is currently running on and so forth. This allows you to have operating system dependent code paths. It is however not recommended to use this facilities.

The Application also has a simple logging method which will print to standard out on the desktop and to logcat on Android."
  (:import (com.badlogic.gdx Application)))

(defprotocol Listener
  "A `Listener` is called when the application is created, resumed, rendering, paused or destroyed. All methods are called in a thread that has the OpenGL context current. You can thus safely create and manipulate graphics resources.

  The `Listener` protocol follows the standard Android activity life-cycle and is emulated on the desktop accordingly."
  (create [_ context] "Called when the application is first created.

                      Parameters:

                      `context` - the gdx context.")

  (dispose [_] "Called when the application is destroyed.")

  (pause [_] "Called when the application is paused.")

  (render [_] "Called when the application should render itself.")

  (resize [_ width height] "Called when the application is resized. This can happen at any point during a non-paused state but will never happen before a call to `create`.

                           Parameters:

                           `width` - the new width in pixels

                           `height` - the new height in pixels")

  (resume [_] "Called when the application is resumed from a paused state. On Android this happens when the activity gets focus again. On the desktop this method will never be called."))

(defn exit
  "Schedule an exit from the application. On android, this will cause a call to `pause` and `dispose` some time in the future,
  it will not immediately finish your application. On iOS this should be avoided in production as it breaks Apples guidelines."
  [context]
  (Application/.exit (:clojure.gdx/app context)))

(defn post-runnable
  "Posts a Runnable on the main loop thread."
  [context runnable]
  (Application/.postRunnable (:clojure.gdx/app context) runnable))
