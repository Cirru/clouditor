
(ns clouditor.component.editor
  (:require [respo.alias :refer [create-comp div]]
            [clouditor.style.layout :as layout]
            [clouditor.style.widget :as widget]
            [respo.component.text :refer [comp-text]]
            [clouditor.component.expression :refer [comp-expression]]))

(defn on-reset [e dispatch! mutate!] (dispatch! :tree/reset nil))

(defn render [tree focused]
  (fn [state mutate!]
    (div
      {:style layout/flex}
      (div
        {:style layout/toolbar}
        (div
          {:style widget/button, :event {:click on-reset}}
          (comp-text "Reset" nil)))
      (div
        {:style layout/container}
        (comp-expression (or tree []) [] focused false)))))

(def comp-editor (create-comp :editor render))
