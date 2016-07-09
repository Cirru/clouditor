
(ns clouditor.updater.stack)

(defn define [store op-data]
  (let [pointer (get-in store [:router :pointer])
        next-module-name (get-in store [:stack (inc pointer)])]
    (if (= next-module-name op-data)
      (-> store (update-in [:router :pointer] inc))
      (-> store
       (update
         :stack
         (fn [stack]
           (let [pointer (get-in store [:router :pointer])]
             (conj (subvec stack 0 (inc pointer)) op-data))))
       (update-in [:router :pointer] inc)))))
