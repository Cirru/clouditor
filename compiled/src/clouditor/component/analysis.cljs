
(ns clouditor.component.analysis
  (:require (hsl.core :refer [hsl])
            [respo.alias :refer [create-comp div]]
            [respo.component.text :refer [comp-text]]
            [respo.component.debug :refer [comp-debug]]
            [clouditor.style.layout :as layout]
            [clouditor.style.widget :as widget]))

(defn init-state [modules] :overview)

(defn update-state [state new-tab] new-tab)

(defn active-style [active?]
  (if active? {:background-color (hsl 200 80 70)}))

(defn on-select [tab] (fn [e dispatch! mutate!] (mutate! tab)))

(defn render [modules]
  (fn [state mutate!]
    (div
      {}
      (div
        {:style widget/tab-list}
        (div
          {:style
           (merge widget/tab (active-style (= state :overview))),
           :event {:click (on-select :overview)}}
          (comp-text "Overview" nil))
        (div
          {:style
           (merge widget/tab (active-style (= state :dependency))),
           :event {:click (on-select :dependency)}}
          (comp-text "Dependency" nil))
        (div
          {:style (merge widget/tab (active-style (= state :unused))),
           :event {:click (on-select :unused)}}
          (comp-text "Unused" nil))))))

(def comp-analysis
 (create-comp :analysis init-state update-state render))