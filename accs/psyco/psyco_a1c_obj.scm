	� G   p�       �V�k�F�ف�7�9S$ى�77ɚ�5�bS(cY>�7K:E:�1]�Jڵ�бB)e�5dcc[��/e_��6��ww����uc?���w�����=i��`8hƨe����-lT�7kNDBV95A��Fu(�1�6���g����`w�0:�\�fiph @�TN2bM8�g8��O��&,bZ�ꩉ�G��2þJ^cQ�H�`I�1f��ݤW��a���.	Hܮz����҄1��X+Z�t<F��^���p�#A�&�-�@V�G𕹼i;Nl�qߡf' .6b��WPHc�ҧ-�T�!Ȧ��5��(/H7��*	��F��&����9	s�� ^7P�4U��[;|����o�:����1Ov����xW#qG=i)w	������
��W9��!���~�f+؎p�t�,�ȂXy�6'
),���:Rz���6�?R?\��e8��`��6Ǜ�X:���^�bFÔ�%�f�J�v�H0��~�mj��LS
j�F��Z�~�#
���mr�BZ��U�Z7���3�����{{��Ox�'�@w0�����o?<�c��}�|ηn���J���� �!t���<�"2G~_e�*��q�������h�/�4��y������M���Q�!�)��#.�X�<` �#�r���?Hi�D"��Q꿱��p�bm���>��itZ�`���ld�rt���_�<���w����ֿ/������_8�����/xr�o}ʓoyr�o���'`M�Xd����M�uɆ��(�)�x吭gG�nx��\4Y�Om���`Ζ���xA���@�6�M2������mq;n-���ųc`A&]���e]s�j��Y�c��裮VM�+�͘�t`^J{\G��6����1SvuT����XR��|��DS�6�=-���b�lJ����zC޶	��n��o���g(v�֕ޒ�e��UEy�fY܍$�h%�M?�8m�t�F�;����k��u�R���=@y�Q���n��fO�r:����g��:=O��%0��g��[�_^�_���[�/�/�,��鈄�X<s�4�>��2\�gHO8������?{؀S�*���_�(��<�=p�ޑ�ϻnʄ�{
�����
   k  Lga2/stage/AmpedScript; e start psyco_explode psyco_explode_execute broken lockItemScript (ZZ)V
 f k l setShadowState
 d n 3 finishAllConnections
 d p 1
 H 0 3 generate A(Ljava/lang/Object;Ljava/lang/String;)Lkotori/gfx/KGLScriptModel;
 d s tD /accs/psyco/knife.scm�    ��      ?�  ?�  ?�               	start_obj lockTimeCount (Z)V
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
 � � �-            �X� �D /accs/psyco/knife.scm�            ?�  ?�  ?�      ´      � hit_bb d� ^    .��       ,�>�         fb1 setFlagsToModifier (ILjava/lang/String;I)V
 f � � psyco    f  g 1  **� mW*� oW*� qW*d� rW*� mW*vw� uW�  h 1  D*� mW*� zW*� }�  L*� �M,� 
*,� �L*i+� �W*� rW*� �W*� �W�  i 1	  �*� qW*q� �W*� �L�M,� �M+� {*� �N+-� �W+-� �� �� �W+� �8+� �+� �� �� ��8+� �� �� �� �Zd�8�� #bj8bj�� ��nj8b8,� �W� �,� �W*,� �W*�ȶ �ɶ �W*� �W*�� rW*1�ζ �W*�� rW*� �W*� �W�  j 1  D*� mW*� zW*� }�  L*� �M,� 
*,� �L*i+� �W*� rW*� �W*� �W� ��U                        �  