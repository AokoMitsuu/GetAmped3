	� G   s�       �VmkG�,C��V�rr�w'�jۍM'�D �bN�=i�����ʱH9;iR-��h�[Zں�~	}�/]d���ݓd;I㦥���N���33�����C��q�LP�-Y	w[ت�1n�<Fb^95F2�:�SF���]k���i�<�_?��B<tyG<q���X��|�g��&�q�p��X.�^g��P'op��&��X��o�y��6� ��(p�D$iW� bD��B��IbG9��� ~u�p���F��� +�F𕙼�z^b�Iϣ+q���ND|l%^����&�cN:V�lB��UQ+��Q^2o)X���8�M�����:	A�n��Y��-6���}���غup-�c����!�m� �A�fk�)w	�ljA�hE� ��?��a/c�%�2��y	]�:�*�$�^��H�B��l����9�>���W�u9΃�)YԢ���i�	�����8�z��j-�[D&n��6�bqj S
j��֚^�~��E@�V�6�|�-��:[	�Z^@#^�����ͽ����ODz_��@wp����o?<����=�~!6o����L���� �!t���<�"3GaOg4 tV9p��n�{#��_.���.o[˥9p�����P�#�)�{C.�8�<` ��)�F|��4G�L�p��o���x�(��,h��9ض39]��~�:���������������ֿ'�����ï ��z���g_�����T�ߊ��ؼ%�O������mG˛%�uY�UF*��!ۚˈ������D�tٔX��.krY���!�2md�d����{w������&ώ�9�LR�U}X0̉��eNL�fY1�H���>�YC6��m.�ׁ�x)�q��ڔ�68�T�7Q�q�Œ�<'���!G�iu�P��#gC��tCx� ��Mx��p��+�F~�b_�_�-u�(�X�*����0В.��������y�kc�33�@�l�Z�/��.ԕTK��l
��FQM���c;x��A�w����y�$>��\=���b����Bu��|}~vaiq���$���3�i������<Cz���`�����8��L���*������z����H�?o�	��)��m�
   k  Lga2/stage/AmpedScript; e start psyco_explode psyco_explode_execute broken lockItemScript (ZZ)V
 f k l setShadowState
 d n 3 finishAllConnections
 d p 1
 H 0 3 generate A(Ljava/lang/Object;Ljava/lang/String;)Lkotori/gfx/KGLScriptModel;
 d s tD /accs/psyco_pur2/knife.scm�    ��      ?�  ?�  ?�               	start_obj lockTimeCount (Z)V
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
 � � �-            �X� �D /accs/psyco_pur2/knife.scm�            ?�  ?�  ?�      ´      � hit_bb d� ^    .��       ,�>�         fb1 setFlagsToModifier (ILjava/lang/String;I)V
 f � � psyco    f  g 1  **� mW*� oW*� qW*d� rW*� mW*vw� uW�  h 1  D*� mW*� zW*� }�  L*� �M,� 
*,� �L*i+� �W*� rW*� �W*� �W�  i 1	  �*� qW*q� �W*� �L�M,� �M+� {*� �N+-� �W+-� �� �� �W+� �8+� �+� �� �� ��8+� �� �� �� �Zd�8�� #bj8bj�� ��nj8b8,� �W� �,� �W*,� �W*�ȶ �ɶ �W*� �W*�� rW*1�ζ �W*�� rW*� �W*� �W�  j 1  D*� mW*� zW*� }�  L*� �M,� 
*,� �L*i+� �W*� rW*� �W*� �W� ��/                        �  