
(ns clouditor.component.summary
  (:require [respo.alias :refer [create-comp div]]
            [clouditor.style.layout :as layout]
            [clouditor.style.devtool :as devtool]
            [respo.component.text :refer [comp-text]]))

(defn render [stack pointer]
  (fn [state mutate!]
    (div
      {:style (merge layout/sidebar devtool/dim9)}
      (->>
        stack
        (map-indexed
          (fn [index module-name] [index
                                   (comp-text module-name nil)]))))))

(def comp-summary (create-comp :summary render))
