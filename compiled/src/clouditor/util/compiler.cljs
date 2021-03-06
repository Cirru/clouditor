
(ns clouditor.util.compiler
  (:require [clojure.string :as string]
            [clojure.set :as set]
            [cljs.reader :refer [read-string]]))

(defn find-dependencies [tree]
  (->>
    tree
    (map
      (fn [item]
        (if (string? item)
          (if (string/includes? item "/") [item] [])
          (find-dependencies item))))
    (apply concat)))

(defn order-modules [dependencies] [])

(defn build-deps [modules]
  (->>
    modules
    (map (fn [entry] [(key entry) (find-dependencies (val entry))]))
    (into {})))

(defn get-main-module [modules]
  (->>
    (keys modules)
    (filter (fn [module-name] (string/includes? module-name "/main")))
    (first)))

(defn expand-graph [module-name deps-tree]
  (comment println "expanding:" module-name deps-tree)
  (let [deps (get deps-tree module-name)]
    (->>
      deps
      (map
        (fn [dependency] [dependency
                          (expand-graph dependency deps-tree)]))
      (into {}))))

(defn build-graph [modules]
  (let [main-module (get-main-module modules)
        deps-tree (build-deps modules)
        tree (expand-graph main-module deps-tree)]
    (assoc {} main-module tree)))

(defn convert-vectors [graph]
  (->>
    graph
    (map (fn [entry] [(convert-vectors (val entry)) (key entry)]))
    (into [])))

(defn generate-code [modules]
  (let [graph (build-graph modules)
        modules-in-order (distinct (flatten (convert-vectors graph)))]
    (->>
      modules-in-order
      (map (fn [module-name] [module-name (get modules module-name)]))
      (into []))))

(defn get-unused-names [modules]
  (let [graph (build-graph modules)
        modules-in-order (into
                           (hash-set)
                           (flatten (convert-vectors graph)))
        all-modules (into (hash-set) (keys modules))]
    (println all-modules modules-in-order)
    (set/difference all-modules modules-in-order)))

(defn eval-expr [env expr]
  (if (string? expr)
    (cond
      (some? (re-find (re-pattern "^\\\\d+$") expr)) (read-string expr)
      (= expr "true") true
      (= expr "false") false
      :else (js/Error. "Not recognized!"))
    (js/Error. "Not implemented yet!")))

(defn evaluate [env ops]
  (if (empty? ops)
    env
    (let [cursor (first ops)
          module-name (first cursor)
          expression (last cursor)
          op-name (first expression)
          op-args (rest expression)]
      (js/Error. "Not implemented yet!"))))
