	� G   r�       �VmkG�,C��V�rr�w'�"���Ʀ��Z"J1�Ӟ����yo�X����4)��BH�h�[Zں�~	}�/]d���ݓd;I㦥���N���33������x�㨙��[�Ucܬy�ļrj��1eu(��XM����3p��y��~�x.��2x4 �:'�&f8���%M�F�ک�\@��"ǡN�ଋM$�$��Z�mҫ@ȱQ�>�HҮĈ��	�2�1v��(:�r<F�A��2 j�3I����/AVh��3y���Ď��GW�.�;�xa��b��9�X��	�6L�W5���yI�q�^M���6�_�=�K��$���f�ڶ��[���b����T��E�����H����Ij��afT��eP���9�u��r�}���-̗��p��%t	�,�ȒX{�6#
,0��&Қ���6�?R?\���8��dQ�6F�Y:$�~�N��%����/��`����4�ũxL)���Zkzͻ]�	Z����:��c�l%�jy� x%g��۷����o=��~���'����t�����E��ؼ#�Gb3����N D�Й�j�`���=�� �Y���J�#썴WX@c|�d��/��m-����jV�~~@]�$����$���(������a2��Q꿱�
p��m�`���ItF�`���td���&�$������#���?��Z������_~`��{p�>�B�w���"�V�7��m�~֬�efo:Z�,Y��˺�2�P�W��\F�F��8��PE�$2�˦�
�tY+�ˊ���	�i#�$����ػ�%oG�%5Y�tn��d��긬�ÂaN\�-sb��e�0"�?��0e�t�����^&���M4^kSʫ��XSe�De�pKZP���t�x��㆜�g�AB��XN�%V��!|��m��ċ�6�V�� žܾ��Q�{�\UT��a�%]&!9I+���1����^gfh���L�^_^�-\�+�
���������ǵS�v�*gs�on+���I|s�zn�����م���������⬙�I�ųgK��Cu-��y��ñ�hG�?p��4�xUU����ѿ�������y6t�S�g�
   k  Lga2/stage/AmpedScript; e start psyco_explode psyco_explode_execute broken lockItemScript (ZZ)V
 f k l setShadowState
 d n 3 finishAllConnections
 d p 1
 H 0 3 generate A(Ljava/lang/Object;Ljava/lang/String;)Lkotori/gfx/KGLScriptModel;
 d s tD /accs/psyco_pur/knife.scm�    ��      ?�  ?�  ?�               	start_obj lockTimeCount (Z)V
 f x y isServer ()Z
 f { | getNearestEnemyPos (I)[Lkotori/geom/Vector3D;
 f ~  '(Ljava/lang/Object;I)Ljava/lang/Object;
 H * � 
itemScript ((Ljava/lang/String;ILjava/lang/Object;)V
 f � � kill
 f � 1 
stopScript
 f � 1 	playSound
 d � 3 getArgv ()Ljava/lang/Object;
 f � ��� kotori.geom.Quaternion?�               Lkotori/geom/Quaternion; � clone
 � � � getPosition ()Lkotori/geom/Position;
 f � �  sub .(Lkotori/geom/Vector3D;)Lkotori/geom/Vector3D;
 � � � Lkotori/geom/KMath; � R2D (F)F
 � � � slopeY2 (F)Lkotori/geom/Vector3D;
 � � � Lkotori/geom/Position; � dir	 � � 	 � R  getDir (Lkotori/geom/Vector3D;)F
 � � � Ljava/lang/Math; � round (F)I
 � � � length ()F
 � � � (FF)F
 � � � min
 � � �B4   getHPBQuaternionD 5(FFFLkotori/geom/Quaternion;)Lkotori/geom/Quaternion;
 � � �B�   	genoffset 1(Lkotori/geom/Vector3D;Lkotori/geom/Quaternion;)V
 d � � attack E(Lkotori/geom/Vector3D;Lga2/stage/atk/AttackParam;)Lga2/stage/Attack;
 f � � Lga2/stage/Attack; � startScript (Ljava/lang/String;)Z
 � � �-            ��� �D /accs/psyco_pur/knife.scm�            ?�  ?�  ?�      ´      � hit_bb d� ^    .��       ,�>�         fb1 setFlagsToModifier (ILjava/lang/String;I)V
 f � � psyco    f  g 1  **� mW*� oW*� qW*d� rW*� mW*vw� uW�  h 1  D*� mW*� zW*� }�  L*� �M,� 
*,� �L*i+� �W*� rW*� �W*� �W�  i 1	  �*� qW*q� �W*� �L�M,� �M+� {*� �N+-� �W+-� �� �� �W+� �8+� �+� �� �� ��8+� �� �� �� �Zd�8�� #bj8bj�� ��nj8b8,� �W� �,� �W*,� �W*�ȶ �ɶ �W*� �W*�� rW*1�ζ �W*�� rW*� �W*� �W�  j 1  D*� mW*� zW*� }�  L*� �M,� 
*,� �L*i+� �W*� rW*� �W*� �W� ��-                        �  