	� G 5/efx/stageitem/trap/target_ball/mdl/target_ball_b.mop��       uSMo�0�pi~��]%n�j��
��6ʁ®��x�i[�ӥL����8����!�L����h��(�������Σ��f�B1�]�IL�Q*h4�I&t`K��!��%�HJ�|�x[�]��{��h�g�s�%�Y�딛O��g��\�������}��V[>cV�\�cG"���,���hZM�^^�
oJ�KbfFg�@W����%�8@��t��A�+��,B�_꺨���)�+�(j� JY�a��d�N2ˬs|b�Ôh*I�J�f��[ju]�
`�;�7���5���DC��	��=�����ǟ�����}�V�-���e^1�p��w��s��� ���.v�.�ӌ��ltpz�AFoZ��!3MSOK"�jNy�i�R>��+����@́��������V;
xK�<��f��e�u��<��{����$<�G�<;{�+��TDR`q��i�i ����t+�`��T|T�p|��^}�j[�*-��5z������'	xx��(D�-;����Tz�l��%��G!Ή�*�ީ�en�ڊ�^��z�Z��uY5괯���g�k�������n/hT� �<��;:�����"6+���aI���R���ᐐ�=   <  Lga2/stage/AmpedScript; e Lkotori/data/MixtureData; p_g�� kotori.data.MixtureData� /efx/mix/bar7.agi � � start 	damagehit destroy guard 
setVisible (Z)V
 f n o
 H 0 3 size (F)V
 f r s?Y�� setShadowState
 d v 3 anime (FFFZ)V
 d x y@�  A�  A   B    generate /(Ljava/lang/Object;)Lkotori/gfx/KGLScriptModel;
 d  �D */efx/stageitem/trap/item_box/ib_smoke2.ptc�            >���>���>���             setJudgmentKind
 f � 3 Lga2/stage/ItemModel; � JUDGE_DAMAGE_HIT	 � �  	playSound
 d � 3 vibe (FFFFI)V
 d � �=�\)CH   enableCollision
 f � o explode
 d � 1 kill
 f � 1 
setMixture (Lkotori/data/MixtureData;)V
 d � � attack 4(Lkotori/geom/Vector3D;Lga2/stage/atk/AttackParam;)V
 f � �-            �  2 � hit_pg    @     � 2 removeMixture
 d � 1 f h g - 1  i� �  j 1  ]*� pW* � qW*u� tW*� wW*� qW*� pW*{|� zW* �� qW*{}~� zW*�� �W*d� qW*� �� �W�  k 1  *� qW*� �W*��,� �W�  l 1  ,*� qW*&� �W*� pW*� �W*� �W*'� qW*� �W�  m 1  #*� qW*� � �W*��� �W*d� qW*� �W�     >L��    >L��>L��>L��� � 	collision�1    >��
    >�  >��
>�  