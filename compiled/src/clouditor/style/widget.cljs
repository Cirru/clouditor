
(ns clouditor.style.widget
  (:require [hsl.core :refer [hsl]]))

(def button
 {:line-height 2,
  :color (hsl 0 0 100),
  :background-color (hsl 200 70 70),
  :padding "0 8px"})

(def expression
 {:border-style "solid",
  :border-width "1px",
  :padding "4px",
  :border-color (hsl 0 0 90),
  :display "inline-block"})
