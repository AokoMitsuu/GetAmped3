	� G   r�       �VmoE��H����.wg'*7Hn���تT!��{�����:�U"���UQ��
E�hB|�x�/�l%�����8I[�����=3��̳�s7�9^�8j&�喬��-lU�7k#1��#aLG�)#V��5���4|�FG!�	���� ���IE�I��!>��FIƸQ�zj,P���q��78�b��,��7���v��
rl���O"���A 1"�q�i��$1������n� ���Z8�LR��s�K�Z#��L�v=/���ѕ�v'">�/�WPL�1'�T6!І���� /�7�ԫI�z�&���GyIs�� `7��,]���b���Yl�ڿ����Hw����6x� I�5̌�����P65� C4��nT�ｏ@�������vN���.B�EYk�Wf$C!c�F��DZ�rՆ�G�+����ߔ,j��h�4K����\�i�Q�@�!����"��Wq�F�85�)�UYkM�y�=�" A�`_�P�pL���V-/���l{o����n��c���7��x"����O���zO�����"}(6�?���@d�)�6v���Q���Uܭ��0��H{�4ƗKz�����ri\�&a����HrJ���D�C����\#>�R�#L�]8J�+� G-�6
&��D�u��LN�A��/7@'���������~�����{h�{�������_��y|�Ho�͏E��H���["��Y���t��Y�>Y�u[e4�������� q����IdL�M���V �/i_= P��l�l��{}pgKގZKj�p���3��T!�qYՇÜ��[��D��È�`�Q߇)kȦ3�����:0/e=n��Z�R^�ǚ*�&*;ΐ�X҂�|���ē�7�$=�J<7�r�l(��n�{Mݶ	O�n��o���Q���+�����UEu��Y�e���2�O?�xm�uf,P;�����+��u�T�R�=By�QTC��v���@�l�����m%uz�6�O`.W�.��X��2�P]:7_��]XZ�5�1	c�x�Li}��e�:O��p86�����?{��W@B�?�q�/���;���G�?�	��	��G��
   k  Lga2/stage/AmpedScript; e start psyco_explode psyco_explode_execute broken lockItemScript (ZZ)V
 f k l setShadowState
 d n 3 finishAllConnections
 d p 1
 H 0 3 generate A(Ljava/lang/Object;Ljava/lang/String;)Lkotori/gfx/KGLScriptModel;
 d s tD /accs/psyco_gre/knife.scm�    ��      ?�  ?�  ?�               	start_obj lockTimeCount (Z)V
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
 � � �-            �X� �D /accs/psyco_gre/knife.scm�            ?�  ?�  ?�      ´      � hit_bb d� ^    .��       ,�>�         fb1 setFlagsToModifier (ILjava/lang/String;I)V
 f � � psyco    f  g 1  **� mW*� oW*� qW*d� rW*� mW*vw� uW�  h 1  D*� mW*� zW*� }�  L*� �M,� 
*,� �L*i+� �W*� rW*� �W*� �W�  i 1	  �*� qW*q� �W*� �L�M,� �M+� {*� �N+-� �W+-� �� �� �W+� �8+� �+� �� �� ��8+� �� �� �� �Zd�8�� #bj8bj�� ��nj8b8,� �W� �,� �W*,� �W*�ȶ �ɶ �W*� �W*�� rW*1�ζ �W*�� rW*� �W*� �W�  j 1  D*� mW*� zW*� }�  L*� �M,� 
*,� �L*i+� �W*� rW*� �W*� �W� ��                        �  