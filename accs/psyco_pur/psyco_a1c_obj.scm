	� G   r�       �VmoE��H����.wg'*7Hn���تT!��{�����:�U"���UQ��
E�hB|�x�/�l%�����8I[�����=3��̳�s7�9^�8j&�喬��-lU�7k#1��#aLG�)#V��5���4|�FG!�	���� ���IE�I��!>��FIƸQ�zj,P���q��78�b��,��7���v��
rl���O"���A 1"�q�i��$1������n� ���Z8�LR��s�K�Z#��L�v=/���ѕ���ND|l%^����&�cN:V�lB��UM+��A^o�W���8�M�u���:	A�n��Y��-6���}���غ�-�#����!�m� �A�fk�)w	ԡljA�hE� ���{�a/c�%�2��y	]�:�*�$�^��H�B��l����9�>���W�u9΃�)YԢ���i�	�����8�z��Cj-�s;D&n��6�bqjS
j��֚^�nz�E@�V�6�|�-��:[	�Z^@#^������������"�/�o�;8�D�c���������H?��E�Pl�^��	��:SXm� �����3:��[I�a����
h�/���y������XM�
���ˑ�޽.�8�<` ��)�F|��4G�L�p��%VZ�Z�mL�4�N�lۙ�.�l�_n�N"�a��;��;"�n�у�����d�?����+ ��ޅ�0�������[�^��D�X�6����hy�d}�.��hB%^9d[sq>� C�Ș.�+��e�@.+^Ҿz@�L�&�v���Ζ����d���0g ��B�㲪�9qU�̉�^/+������S֐Mgj�˹�u`"^�z�D�6����5U�MTv�!g����(Oǉ'i;n�IzZ$�xn����Pb5�"����m�x1����J���ؗ�WzC�3�y/����>�1̳��$$'ie8�8~������X�v6S�חWj�J����{6��~����q�ԩ>����[��J��<m��\��]|g�~yev��tn�>?���8kfc�b�̙�4�P]�pu�"=�pl0�������&���*�����_z�w����y6t��v�`�
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
 � � �-            �X� �D /accs/psyco_pur/knife.scm�            ?�  ?�  ?�      ´      � hit_bb d� ^    .��       ,�>�         fb1 setFlagsToModifier (ILjava/lang/String;I)V
 f � � psyco    f  g 1  **� mW*� oW*� qW*d� rW*� mW*vw� uW�  h 1  D*� mW*� zW*� }�  L*� �M,� 
*,� �L*i+� �W*� rW*� �W*� �W�  i 1	  �*� qW*q� �W*� �L�M,� �M+� {*� �N+-� �W+-� �� �� �W+� �8+� �+� �� �� ��8+� �� �� �� �Zd�8�� #bj8bj�� ��nj8b8,� �W� �,� �W*,� �W*�ȶ �ɶ �W*� �W*�� rW*1�ζ �W*�� rW*� �W*� �W�  j 1  D*� mW*� zW*� }�  L*� �M,� 
*,� �L*i+� �W*� rW*� �W*� �W� ���                        �  