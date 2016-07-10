
(ns clouditor.style.typeset
  (:require [hsl.core :refer [hsl]]))

(def section-title
 {:line-height 2,
  :font-size "24px",
  :font-weight "lighter",
  :margin-top "8px"})

(def hint {:color (hsl 0 0 80)})
