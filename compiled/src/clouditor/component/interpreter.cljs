
(ns clouditor.component.interpreter
  (:require [respo.alias :refer [create-comp div textarea]]
            [respo.component.text :refer [comp-text]]
            [respo.component.space :refer [comp-space]]
            [clouditor.style.layout :as layout]
            [clouditor.style.widget :as widget]
            [clouditor.util.compiler :refer [find-dependencies
                                             build-deps build-graph]]))

(defn init-state [modules] [])

(defn update-state [state new-code] new-code)

(defn on-compile [modules]
  (fn [e dispatch! mutate!]
    (let [] (println "Compile..." (build-graph modules)))))

(defn render [modules]
  (fn [state mutate!]
    (div
      {}
      (textarea
        {:style widget/codebox,
         :attrs
         {:placeholder "Click button to generate code!",
          :value (pr-str state)}})
      (div
        {:style layout/toolbar}
        (div
          {:style widget/button, :event {:click (on-compile modules)}}
          (comp-text "Generate Code" nil))
        (comp-space 8 nil)
        (div {:style widget/button} (comp-text "Run" nil))))))

(def comp-interpreter
 (create-comp :interpreter init-state update-state render))
