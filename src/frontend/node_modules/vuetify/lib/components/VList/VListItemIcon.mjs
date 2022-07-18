import { createVNode as _createVNode, mergeProps as _mergeProps, resolveDirective as _resolveDirective } from "vue";
// Components
import { makeVIconProps, VIcon } from "../VIcon/VIcon.mjs"; // Utilities

import { defineComponent, useRender } from "../../util/index.mjs";
export const VListItemIcon = defineComponent({
  name: 'VListItemIcon',
  props: makeVIconProps(),

  setup(props, _ref) {
    let {
      slots
    } = _ref;
    useRender(() => _createVNode(VIcon, _mergeProps({
      "class": ['v-list-item-icon', {
        'v-list-item-icon--start': props.start,
        'v-list-item-icon--end': props.end
      }]
    }, props), slots));
    return {};
  }

});
//# sourceMappingURL=VListItemIcon.mjs.map