	� G  /efx/stageitem/mdl/trap_base.kmd V�       �VKo�@�!%R�ê���8���P	T^-��z����Ye�]v7m
�ͅ�'8����&�Ŀ`v���x�"P$g=3��曇]=}y�IF#_���ٕ��%�IS_�.UYW
eHG��������T:{���S,Uoo�)�)p�e8͡`)Ase�ՇKՊ����/X�Wj���}~q����O/_���������h8��-��1�ȼUt5�z���Cf�MSq�g���b���<D�ܹ{��֍��f����de���9dp/נկ����E��4�t�3��:�.׉���Z':��O��sHLlT�w�L��p��t�O�0�pc	ޮ_�4�2 ��`�<֏�:9�?���\3(��u5�&�����K����Jp��c�aX��
�����,j���9d����ɩ��l4|;~O�
�A��P��Ăp�+��M�>��>�=��!q�UX��1g<�Ǥ�ő�EH���=��S/����Q)��I� N��R=���L��\+shk�YpM��G�r���X��).A�M`�Jd��������	["��IJ�H�-��B�\�]X���q�NR�wu)h��q�H��L�79��]��%:���?3��J�����@_r\W�dc�s�Y�� g�]�q�i-��M���U�kQ�a�y�3�v�Z��v-��(��ȯ��$s].�埍���m������>�����3
�k��yd�la�G�7N������\Z2��������D�������OwG�����c3%ܧ�����dO��f���6t@�h�6P�۸,���-��R0���K�.�fs�}�|�h�
   N  Lga2/stage/AmpedScript; e Lkotori/gfx/KGLScriptModel; sphere start equipped startattack hitstage reflect approach broken destroy install tempInt (I)Ljava/lang/Integer;
 d r s setShadowState
 d u 3 setRenderType
 d w 3 generate /(Ljava/lang/Object;)Lkotori/gfx/KGLScriptModel;
 d y zD ,/efx/stageitem/trap/trap_sphere/trap_efx.scm�            ?�  ?�  ?�              AD //efx/stageitem/trap/trap_sphere/trap_shield.scm�    >8Q�    >
=q>
=q>
=q            AD //efx/stageitem/trap/trap_sphere/trap_sphere.scm�    >L��    ?�  ?�  ?�              
 H 0 3 roll (Lkotori/geom/Vector3D;I)V
 f � �-    Dz       temp ()Ljava/lang/Object;
 d � � Ljava/lang/Integer; � intValue
 � � 2 	playSound
 d � 3 
trapAttack E(Lkotori/geom/Vector3D;Lga2/stage/atk/AttackParam;)Lga2/stage/Attack;
 f � �-            �� ga2.stage.atk.DiskAttack  �/ � hit_brx�  d�   �� �     kill
 f � 1 finishAllKGL
 f � 1 explode
 d � 1 
setVisible (Z)V
 f � � changePickable
 f � �D efx/flash/glow01_green9.ptc�    >�=q    ?333?333?333            D efx/flash/glow01_green9.ptc�    >�=q    ?333?333?333            D efx/flash/glow01_green9.ptc�    >�=q    ?L��?L��?L��            D efx/flash/glow01_green9.ptc�    >�=q    ?fff?fff?fff            D 4efx/stageitem/trap/trap_sphere/trap_sphere_white.scm�    >L��    ?�  ?�  ?�              D efx/flash/glow01_green9.ptc�    >�=q    ?fff?fff?fff            D efx/flash/glow01_green9.ptc�    >�=q    ?fff?fff?fff            D efx/stageitem/ripple02_gre.ptc�    >�=q    ?L��?L��?L��             g
 � � 1 rotate (Lkotori/geom/Quaternion;I)V
 f � ��� kotori.geom.Quaternion?�               trapVisibleTeamMember
 f � 1 addTrapToMiniMap
 f � 1 setJudgmentApproach (F)V
 f � � f h g	 i 1  *� tW*� W�  j 1  #*� vW*� xW*|� {W*}� {W*~� {� �  k 1  *� W*� W*�� �W*� tW�  l 1  *� W*� �L+� �� *� W�  m 1  *� W*{� �W�  n 1  2� ' *� W*��� �W* ȶ W*� �W*d� W*� W*� �W�  o 1  *� W�  p 1  %*� W*&� �W*� �W*� �W*d� W*� �W�  q 1  �*� �W*� W*�� {W*�� {W*� W*�� {W*�� {W*�� {W*� W*�� {W*�� {W* ȶ W*d� W*�� {W� � �W*� �W*�d� �W*� �W*� �W*	Ķ W*� �W� ,R�    >k�    >��R>��R>��R�  