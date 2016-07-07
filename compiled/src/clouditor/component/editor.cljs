
(ns clouditor.component.editor
  (:require [respo.alias :refer [create-comp div]]
            [clouditor.style.layout :as layout]
            [clouditor.style.widget :as widget]
            [respo.component.text :refer [comp-text]]
            [clouditor.component.expression :refer [comp-expression]]))

(defn render [tree focused]
  (fn [state mutate!]
    (div
      {:style layout/flex}
      (div
        {:style layout/toolbar}
        (div {:style widget/button} (comp-text "Save" nil)))
      (div
        {:style layout/container}
        (comp-expression (or tree []) [] focused)))))

(def comp-editor (create-comp :editor render))
