
(ns clouditor.util.compiler
  (:require [clojure.string :as string]))

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
