	� G 5/efx/stageitem/trap/target_ball/mdl/target_ball_y.mop��       uSAo�0�pi~��]%n�j��
�ƀq��k�6�g�Ė�t)�.�8!�8 qG�?!�/i�uC�j�����}�� ���y�#��a�g�&����D�e�L
e�� ~axꏕ"��\C��ݞ}x'�CvBsa��]�
�^��T�V����K��^��W�oM�u�KS(z�H����N�q�������Ed��D��q;�z���#!�_#tK?B�6h� �x,O�W�i��9�C炤���%���!ư�R�ܤ�c7g�0j��8Icm�ș����-u�~xp@��$�jb���w��Z� ���ǟ�����]�6�p�l�٨���#9Ϩ����;���m���cow=Fs������P-8�jen\�f�QD�.��2��XgbNC_��!)��7BzF����覣����	�hn��<qN�ߙHh�?}}tr��ư<9}�7�S	ɀ�%oܺ�@�d9t��p������f�{��ή�}Z����𞬝>4'��4���w�L���F`<Mm�Y7��y�:;XATR��M5){��V���;�oh4�����y=��~]��"�]:C�^�On��q�Qm�#�c���z&G6��dv��hH��J�L,�B=�_��  =   <  Lga2/stage/AmpedScript; e Lkotori/data/MixtureData; p_g�� kotori.data.MixtureData� /efx/mix/bar7.agi � � start 	damagehit destroy guard 
setVisible (Z)V
 f n o
 H 0 3 size (F)V
 f r s?�ff setShadowState
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
 f � �-            �  2 � hit_pg    @�     � 2 removeMixture
 d � 1 f h g - 1  i� �  j 1  ]*� pW* � qW*u� tW*� wW*� qW*� pW*{|� zW* �� qW*{}~� zW*�� �W*d� qW*� �� �W�  k 1  *� qW*� �W*��,� �W�  l 1  ,*� qW*&� �W*� pW*� �W*� �W*'� qW*� �W�  m 1  #*� qW*� � �W*��� �W*d� qW*� �W�     >�Q�    >�\)>�Q�>�\)� � 	collision�1    >�33    >�\)>�33>�\)