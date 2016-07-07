
(ns clouditor.updater.core
  (:require [clouditor.updater.tree :as tree]))

(defn identity-updater [store op-data] store)

(defn updater [store op op-data]
  (let [handler (case
                  op
                  :tree/append
                  tree/append
                  :tree/prepend
                  tree/prepend
                  :tree/rm
                  tree/rm
                  :tree/fold
                  tree/fold
                  :tree/unfold
                  tree/unfold
                  identity-updater)]
    ))