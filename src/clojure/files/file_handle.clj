(ns clojure.files.file-handle
  "Represents a file or directory on the filesystem, classpath, Android app storage, or Android assets directory. FileHandles are created via a Files instance. Because some of the file types are backed by composite files and may be compressed (for example, if they are in an Android .apk or are found via the classpath), the methods for extracting a path() or file() may not be appropriate for all types. Use the Reader or Stream methods here to hide these dependencies from your platform independent code."
  (:refer-clojure :exclude [list]))

(defprotocol FileHandle
  (list [_]
        "Returns the paths to the children of this directory. Returns an empty list if this file handle represents a file and not a directory. On the desktop, an Files.FileType.Internal handle to a directory on the classpath will return a zero length array.")

  (directory? [_]
              "Returns true if this file is a directory. Always returns false for classpath files. On Android, an Files.FileType.Internal handle to an empty directory will return false. On the desktop, an Files.FileType.Internal handle to a directory on the classpath will return false.")

  (extension [_]
             "Returns the file extension (without the dot) or an empty string if the file name doesn't contain a dot.")

  (path [_]
        "The path of the file as specified on construction, e.g. Gdx.files.internal(\"dir/file.png\") -> dir/file.png. backward slashes will be replaced by forward slashes."))



