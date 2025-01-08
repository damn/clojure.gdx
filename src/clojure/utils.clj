(ns clojure.utils)

; TODO check params & pass & check @ defcomponent ( forgot 1 arg - can be checked statically)
(defmacro defsystem
  {:arglists '([name docstring?])}
  [name-sym & args]
  (let [docstring (if (string? (first args))
                    (first args))]
    `(defmulti ~name-sym
       ~(str "[[defsystem]]" (when docstring (str "\n\n" docstring)))
       (fn [[k#] & args#]
         k#))))

(def overwrite-warnings? false)

(defmacro defcomponent [k & sys-impls]
  `(do
    ~@(for [[sys & fn-body] sys-impls
            :let [sys-var (resolve sys)]]
        `(do
          (when (and overwrite-warnings?
                     (get (methods @~sys-var) ~k))
            (println "warning: overwriting defmethod" ~k "on" ~sys-var))
          (defmethod ~sys ~k ~(symbol (str (name (symbol sys-var)) "." (name k)))
            ~@fn-body)))
    ~k))

(defn safe-get [m k]
  (let [result (get m k ::not-found)]
    (if (= result ::not-found)
      (throw (IllegalArgumentException. (str "Cannot find " (pr-str k))))
      result)))

(defn mapvals [f m]
  (into {} (for [[k v] m]
             [k (f v)])))

(defn tile->middle [position]
  (mapv (partial + 0.5) position))

(defn define-order [order-k-vector]
  (apply hash-map (interleave order-k-vector (range))))

(defn sort-by-order [coll get-item-order-k order]
  (sort-by #((get-item-order-k %) order) < coll))

#_(defn order-contains? [order k]
    ((apply hash-set (keys order)) k))

#_(deftest test-order
    (is
     (= (define-order [:a :b :c]) {:a 0 :b 1 :c 2}))
    (is
     (order-contains? (define-order [:a :b :c]) :a))
    (is
     (not
      (order-contains? (define-order [:a :b :c]) 2)))
    (is
     (=
      (sort-by-order [:c :b :a :b] identity (define-order [:a :b :c]))
      '(:a :b :b :c)))
    (is
     (=
      (sort-by-order [:b :c :null :null :a] identity (define-order [:c :b :a :null]))
      '(:c :b :a :null :null))))

(defn safe-merge [m1 m2]
  {:pre [(not-any? #(contains? m1 %) (keys m2))]}
  (merge m1 m2))

(defn index-of [k ^clojure.lang.PersistentVector v]
  (let [idx (.indexOf v k)]
    (if (= -1 idx)
      nil
      idx)))

(defn sort-by-k-order [k-order components]
  (let [max-count (inc (count k-order))]
    (sort-by (fn [[k _]] (or (index-of k k-order) max-count))
             components)))

(defn find-first
  "Returns the first item of coll for which (pred item) returns logical true.
  Consumes sequences up to the first match, will consume the entire sequence
  and return nil if no match is found."
  [pred coll]
  (first (filter pred coll)))

; libgdx fn is available:
; (MathUtils/isEqual 1 (length v))
(defn- approx-numbers [a b epsilon]
  (<=
   (Math/abs (float (- a b)))
   epsilon))

(defn- round-n-decimals [^double x n]
  (let [z (Math/pow 10 n)]
    (float
     (/
      (Math/round (float (* x z)))
      z))))

(defn readable-number [^double x]
  {:pre [(number? x)]} ; do not assert (>= x 0) beacuse when using floats x may become -0.000...000something
  (if (or
       (> x 5)
       (approx-numbers x (int x) 0.001)) ; for "2.0" show "2" -> simpler
    (int x)
    (round-n-decimals x 2)))

(defn assoc-ks [m ks v]
  (if (empty? ks)
    m
    (apply assoc m (interleave ks (repeat v)))))

(defn truncate [s limit]
  (if (> (count s) limit)
    (str (subs s 0 limit) "...")
    s))

(defn ->edn-str [v]
  (binding [*print-level* nil]
    (pr-str v)))

(defn- indexed ; from clojure.contrib.seq-utils (discontinued in 1.3)
  "Returns a lazy sequence of [index, item] pairs, where items come
  from 's' and indexes count up from zero.

  (indexed '(a b c d)) => ([0 a] [1 b] [2 c] [3 d])"
  [s]
  (map vector (iterate inc 0) s))

(defn utils-positions ; from clojure.contrib.seq-utils (discontinued in 1.3)
  "Returns a lazy sequence containing the positions at which pred
  is true for items in coll."
  [pred coll]
  (for [[idx elt] (indexed coll) :when (pred elt)] idx))

(defmacro when-seq [[aseq bind] & body]
  `(let [~aseq ~bind]
     (when (seq ~aseq)
       ~@body)))

(defn dissoc-in [m ks]
  (assert (> (count ks) 1))
  (update-in m (drop-last ks) dissoc (last ks)))

(def dev-mode? (= (System/getenv "DEV_MODE") "true"))

(defmacro with-err-str
  "Evaluates exprs in a context in which *err* is bound to a fresh
  StringWriter.  Returns the string created by any nested printing
  calls."
  [& body]
  `(let [s# (new java.io.StringWriter)]
     (binding [*err* s#]
       ~@body
       (str s#))))
