
(ns pretty-css.attributes)

;; ----------------------------------------------------------------------------
;; ----------------------------------------------------------------------------

(defn apply-preset
  ; @param (map) presets
  ; @param (map) element-props
  ; {:preset (keyword)(opt)}
  ;
  ; @usage
  ; (apply-preset {:my-preset {...}}
  ;               {:preset :my-preset ...})
  ;
  ; @example
  ; (apply-preset {:my-preset {:hover-color :highlight}}
  ;               {:preset :my-preset})
  ; =>
  ; {:hover-color :highlight
  ;  :preset      :my-preset}
  ;
  ; @return (map)
  [presets {:keys [preset] :as element-props}]
  (if preset (let [preset-props (get presets preset)]
                  (merge preset-props element-props))
             element-props))

(defn apply-color
  ; @param (map) element-attributes
  ; {:style (map)(opt)}
  ; @param (keyword) color-key
  ; @param (keyword) color-data-key
  ; @param (keyword or string) color-value
  ;
  ; @usage
  ; (apply-color {:style {...}} :color :data-color :muted)
  ;
  ; @example
  ; (apply-color {...} :color :data-color :muted)
  ; =>
  ; {:data-color :muted}
  ;
  ; @example
  ; (apply-color {...} :color :data-color "#fff")
  ; =>
  ; {:data-color :var :style {"--color" "fff"}}
  ;
  ; @example
  ; (apply-color {:style {:padding "12px"}} :color :data-color "#fff")
  ; =>
  ; {:data-color :var :style {"--color" "fff" :padding "12px"}}
  ;
  ; @return (map)
  ; {:style (map)}
  [element-attributes color-key color-data-key color-value]
  (cond (keyword? color-value) (-> element-attributes (assoc-in [color-data-key] color-value))
        (string?  color-value) (-> element-attributes (assoc-in [:style (str "--" (name color-key))] color-value)
                                                      (assoc-in [color-data-key] :var))
        :return element-attributes))

(defn apply-dimension
  ; @param (map) element-attributes
  ; @param (keyword) dimension-key
  ; @param (keyword) dimension-data-key
  ; @param (keyword, px or string) dimension-value
  ;
  ; @usage
  ; (apply-dimension {:style {...} :width :data-block-width 42})
  ;
  ; @example
  ; (apply-dimension {...} :width :data-block-width 42)
  ; =>
  ; {:style {:width "42px"}}
  ;
  ; @example
  ; (apply-dimension {...} :width :data-block-width "42%")
  ; =>
  ; {:style {:width "42%"}}
  ;
  ; @example
  ; (apply-dimension {...} :width :data-block-width :s)
  ; =>
  ; {:data-block-width :s}
  ;
  ; @return (map)
  [element-attributes dimension-key dimension-data-key dimension-value]
  (cond (keyword? dimension-value) (assoc    element-attributes dimension-data-key dimension-value)
        (integer? dimension-value) (assoc-in element-attributes [:style dimension-key] (str dimension-value "px"))
        (string?  dimension-value) (assoc-in element-attributes [:style dimension-key]      dimension-value)))

;; ----------------------------------------------------------------------------
;; ----------------------------------------------------------------------------

(defn badge-attributes
  ; @param (map) element-props
  ; {:badge-color (keyword)
  ;   :default, :highlight, :invert, :muted, :primary, :secondary, :success, :warning
  ;  :badge-content (string)
  ;  :badge-position (keyword)
  ;   :tl, :tr, :br, :bl}
  ;
  ; @usage
  ; (badge-attributes {...})
  ;
  ; @example
  ; (badge-attributes {:badge-color :primary :badge-content "420" :badge-position :tr})
  ; =>
  ; {:data-badge-color    :primary
  ;  :data-badge-content  "420"
  ;  :data-badge-position :tr}
  ;
  ; @return (map)
  ; {:data-badge-color (keyword)
  ;  :data-badge-content (string)
  ;  :data-badge-position (keyword)}
  [{:keys [badge-color badge-content badge-position]}]
  {:data-badge-content  badge-content
   :data-badge-color    badge-color
   :data-badge-position badge-position})

(defn marker-attributes
  ; @param (map) element-props
  ; {:marker-color (keyword)(opt)
  ;   :default, :highlight, :invert, :muted, :primary, :secondary, :success, :warning
  ;  :marker-position (keyword)(opt)
  ;   :tl, :tr, :br, :bl}
  ;
  ; @usage
  ; (marker-attributes {...})
  ;
  ; @example
  ; (marker-attributes {:marker-color :primary :marker-position :tr})
  ; =>
  ; {:data-marker-color    :primary
  ;  :data-marker-position :tr}
  ;
  ; @return (map)
  ; {:data-marker-color (keyword)
  ;  :data-marker-position (keyword)}
  [{:keys [marker-color marker-position]}]
  {:data-marker-color    marker-color
   :data-marker-position marker-position})

;; ----------------------------------------------------------------------------
;; ----------------------------------------------------------------------------

(defn default-attributes
  ; @param (map) element-props
  ; {:class (keyword or keywords in vector)(opt)
  ;  :disabled? (boolean)(opt)}
  ;
  ; @usage
  ; (default-attributes {...})
  ;
  ; @example
  ; (default-attributes {:class :my-class :disabled? true})
  ; =>
  ; {:class :my-element
  ;  :data-disabled true}
  ;
  ; @example
  ; (default-attributes {:class [:my-class] :disabled? true})
  ; =>
  ; {:class [:my-element]
  ;  :data-disabled true}
  ;
  ; @return (map)
  ; {:class (keyword or keywords in vector)
  ;  :data-disabled (boolean)}
  [{:keys [class disabled?]}]
  {:class         class
   :data-disabled disabled?})

;; ----------------------------------------------------------------------------
;; ----------------------------------------------------------------------------

(defn indent-attributes
  ; @param (map) element-props
  ; {:indent (map)(opt)
  ;   {:bottom (keyword)(opt)
  ;     :xxs, :xs, :s, :m, :l, :xl, :xxl
  ;    :left (keyword)(opt)
  ;     :xxs, :xs, :s, :m, :l, :xl, :xxl
  ;    :right (keyword)(opt)
  ;     :xxs, :xs, :s, :m, :l, :xl, :xxl
  ;    :top (keyword)(opt)
  ;     :xxs, :xs, :s, :m, :l, :xl, :xxl}}
  ;
  ; @usage
  ; (indent-attributes {:indent {...}})
  ;
  ; @example
  ; (indent-attributes {:indent {:horizontal :xxl :left :xs}})
  ; =>
  ; {:data-indent-horizontal :xxl
  ;  :data-indent-left       :xs}
  ;
  ; @return (map)
  [{:keys [indent]}]
  (letfn [(f [result key value]
             (assoc result (keyword (str "data-indent-" (name key))) value))]
         (reduce-kv f {} indent)))

(defn outdent-attributes
  ; @param (map) element-props
  ; {:outdent (map)(opt)
  ;   {:bottom (keyword)(opt)
  ;     :xxs, :xs, :s, :m, :l, :xl, :xxl
  ;    :left (keyword)(opt)
  ;     :xxs, :xs, :s, :m, :l, :xl, :xxl
  ;    :right (keyword)(opt)
  ;     :xxs, :xs, :s, :m, :l, :xl, :xxl
  ;    :top (keyword)(opt)
  ;     :xxs, :xs, :s, :m, :l, :xl, :xxl}}
  ;
  ; @usage
  ; (outdent-attributes {:outdent {...}})
  ;
  ; @example
  ; (outdent-attributes {:outdent {:horizontal :xxl :left :xs}})
  ; =>
  ; {:data-outdent-horizontal :xxl
  ;  :data-outdent-left       :xs}
  ;
  ; @return (map)
  [{:keys [outdent]}]
  (letfn [(f [result key value]
             (assoc result (keyword (str "data-outdent-" (name key))) value))]
         (reduce-kv f {} outdent)))
