
(ns clouditor.updater.core
  (:require [clouditor.updater.tree :as tree]))

(defn identity-updater [store op-data] store)

(defn updater [store op op-data]
  (let [handler (case
                  op
                  :tree/expr-append
                  tree/expr-append
                  :tree/expr-prepend
                  tree/expr-prepend
                  :tree/after
                  tree/after
                  :tree/before
                  tree/before
                  :tree/rm
                  tree/rm
                  :tree/fold
                  tree/fold
                  :tree/expr-unfold
                  tree/expr-unfold
                  :tree/token-modify
                  tree/token-modify
                  :tree/focus
                  tree/focus
                  identity-updater)]
    (handler store op-data)))
