
(ns clouditor.component.container
  (:require [hsl.core :refer [hsl]]
            [respo.alias :refer [create-comp div span]]
            [clouditor.component.summary :refer [comp-summary]]
            [clouditor.component.editor :refer [comp-editor]]
            [clouditor.style.layout :as layout]
            [clouditor.style.devtool :as devtool]))

(defn render [store]
  (fn [state mutate!]
    (let [modules (:modules store)
          stack (:stack store)
          router (:router store)]
      (div
        {:style (merge layout/fullscreen layout/row)}
        (comp-summary stack (:pointer router))
        (comp-editor (get-in modules (get stack (:pointer router))))))))

(def comp-container (create-comp :container render))
