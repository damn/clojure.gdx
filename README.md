# clojure.gdx

![logo](https://github.com/user-attachments/assets/af7d27d3-8662-4fb2-a3a4-965477d8feac)

[Clojure](https://clojure.org/) API for [libgdx](https://libgdx.com/). Work in progress.

> libGDX is a cross-platform Java game development framework based on OpenGL (ES) that works on Windows, Linux, macOS, Android, your browser and iOS.

## [API Docs](https://damn.github.io/clojure.gdx/)

There are 5 parts which may be split up into separate repositories:

* the main protocols - clojure.* namespaces - only protocols - abstract cross-plattform game engine
* clojure.gdx - implements the protocols through reified libgdx objects, libgdx helpers
* clojure.gdx.lwjgl - start a lwjgl application for libgdx (libgdx can also be started as android or ios applicaation)
* clojure.gdx.freetype - freetype extension
* clojure.gdx.shape-drawer
* clojure.gdx.ui -> vis-ui usage, TODO may use protocols also
