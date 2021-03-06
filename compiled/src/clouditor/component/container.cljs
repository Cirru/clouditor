
(ns clouditor.component.container
  (:require [hsl.core :refer [hsl]]
            [respo.alias :refer [create-comp div span]]
            [clouditor.component.summary :refer [comp-summary]]
            [clouditor.component.editor :refer [comp-editor]]
            [clouditor.component.analysis :refer [comp-analysis]]
            [clouditor.style.layout :as layout]
            [clouditor.style.devtool :as devtool]
            [respo.component.debug :refer [comp-debug]]))

(defn render [store]
  (fn [state mutate!]
    (let [modules (:modules store)
          stack (:stack store)
          router (:router store)]
      (div
        {:style (merge layout/fullscreen layout/row)}
        (comp-summary stack (:pointer router))
        (case
          (:page router)
          :editor
          (comp-editor
            (get modules (get stack (:pointer router)))
            (:coord router))
          :analysis
          (comp-analysis modules)
          nil)
        (comp-debug store {:bottom 0})))))

(def comp-container (create-comp :container render))
