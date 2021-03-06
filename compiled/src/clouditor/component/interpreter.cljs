
(ns clouditor.component.interpreter
  (:require [respo.alias :refer [create-comp div textarea]]
            [respo.component.text :refer [comp-text]]
            [respo.component.space :refer [comp-space]]
            [clouditor.style.layout :as layout]
            [clouditor.style.typeset :as typeset]
            [clouditor.style.widget :as widget]
            [clouditor.util.compiler :refer [find-dependencies
                                             build-graph
                                             generate-code]]))

(defn on-run [e dispatch! mutate!]
  (js/alert "Interpreter is not implemented yet!"))

(defn render [modules]
  (fn [state mutate!]
    (div
      {}
      (div {:style typeset/section-title} (comp-text "AST" nil))
      (textarea
        {:style widget/codebox,
         :attrs
         {:placeholder "Click button to generate code!",
          :value (generate-code modules)}})
      (div
        {:style layout/toolbar}
        (div
          {:style widget/button, :event {:click on-run}}
          (comp-text "Run" nil))))))

(def comp-interpreter (create-comp :interpreter render))
