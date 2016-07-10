
(ns clouditor.component.analysis
  (:require (hsl.core :refer [hsl])
            [respo.alias :refer [create-comp div]]
            [respo.component.text :refer [comp-text]]
            [respo.component.space :refer [comp-space]]
            [respo.component.debug :refer [comp-debug]]
            [clouditor.style.layout :as layout]
            [clouditor.style.widget :as widget]
            [clouditor.style.typeset :as typeset]
            [clouditor.component.overview :refer [comp-overview]]
            [clouditor.component.tabs :refer [comp-tabs]]
            [clouditor.component.interpreter :refer [comp-interpreter]]))

(def tabs ["Overview" "Interpreter"])

(defn init-state [modules] (first tabs))

(defn update-state [state new-tab] new-tab)

(defn on-select [mutate-this!] (fn [tab] (mutate-this! tab)))

(defn render [modules]
  (fn [state mutate!]
    (div
      {:style (merge layout/flex layout/container)}
      (comp-tabs tabs state (on-select mutate!))
      (comp-space nil 24)
      (div
        {}
        (case
          state
          "Overview"
          (comp-overview modules)
          "Interpreter"
          (comp-interpreter modules)
          nil))
      (comment comp-debug modules nil))))

(def comp-analysis
 (create-comp :analysis init-state update-state render))
