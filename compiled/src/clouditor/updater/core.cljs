
(ns clouditor.updater.core
  (:require [clouditor.updater.tree :as tree]
            [clouditor.updater.stack :as stack]
            [clouditor.updater.router :as router]))

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
                  :tree/reset
                  tree/reset
                  :stack/define
                  stack/define
                  :stack/display
                  stack/display
                  :stack/display-stack
                  stack/display-stack
                  :router/point
                  router/point
                  :router/page
                  router/page
                  identity-updater)]
    (handler store op-data)))
