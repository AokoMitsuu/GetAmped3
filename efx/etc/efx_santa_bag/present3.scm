	� G &/efx/etc/efx_santa_bag/mdl/box_sx2.kmd�       �S�n�0���¹Ѕ+��"����؎Q�l!W���
!�$H:q��{늊_�P����h���l-�Đ��<cY
ޠP�,c͕��z�JjK2i��^�,�>��x����[�t��2�f����z�<!�iK�/�څ{�_�@�,7��ȋ\���BQ)�Vo�Eʯc���2�r1���f�)f�v:.�$��,b�$=*l�~��P�s<�f��d|�\��Ӓ��g��|4�S72��v[n���K���i��ˀ`(�s%�Q�u6����ݙu+-3xwH���_L=�C^�э�g�� @��B.���&���F��6ظ�#�]fъ�mت\&���h�DIC�;^;���U�|bb���3��G@l'�';WX��y�Z��|=��[���t�NM���nW��8�w ���
�w�{��E.o��Sb��	y!	+pD��*���*h��S��t0�E4�,�>ᩐ��IP������"��ҟ<��WKi��Tx��V�h�z��n�~��'��   4  Lga2/stage/AmpedScript; e start broken 
setVisible (Z)V
 f i j lockItemScript (ZZ)V
 f l m rotate (Lkotori/geom/Quaternion;)V
 f o p�� kotori.geom.Quaternion?5�    �5�    
 H 0 3 setEdge
 f t j setRenderType
 d v 3 setBlend
 d x 3 setColor (FFFF)V
 d z { setShadowState
 d } 3 enableCollision
 f  j lockTimeCount
 f � j generate /(Ljava/lang/Object;)Lkotori/gfx/KGLScriptModel;
 d � �D "/efx/etc/efx_santa_bag/explode.scm�    >���    ?�  ?�  ?�                	playSound
 d � 3D "/efx/etc/efx_santa_bag/explode.scm�    ?L��    ?�  ?�  ?�               D "/efx/etc/efx_santa_bag/explode.scm�    ?�ff    ?�  ?�  ?�                attack E(Lkotori/geom/Vector3D;Lga2/stage/atk/AttackParam;)Lga2/stage/Attack;
 f � �-    ?���    �� ga2.stage.atk.DiskAttack
�� � hit_fg   d�  �� d    -    ?���    ��@� �  d�  �� d     explode
 d � 3 kill
 f � 1 
stopScript
 f � 1 f  g 1  P*� kW*� nW*r� qW*d� sW*� kW*� uW*� wW*� yW*� |W*� ~W*� �W*� nW�  h 1  �*� kW*� �W*� nW*� �W*�� �W*0� �W* �� sW*�� �W* �� sW*�� �W* �� sW*!� �W*��� �W*��� �W*d� sW*� �W*� sW*� �W*� �W� !�    ?&ff    >#�
?&ff>#�
� � 	collision�1    ?�    =�?�=�